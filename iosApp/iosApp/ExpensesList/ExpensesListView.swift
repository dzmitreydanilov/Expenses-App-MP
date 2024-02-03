import Foundation
import SwiftUI
import shared

struct ExpensesListView : View {
    
    @ObservedObject
    private var viewModel: ExpensesListViewModel
    
    init(_ component: CategoryList) {
        self.viewModel = ExpensesListViewModel(component)
    }
    
    var body: some View {
        VStack {
            VStack {
                switch viewModel.state {
                case  _ as CategoriesState.Loading: ProgressView()
                case let state as CategoriesState.Loaded: ExpensesListContent(categories: state)
                default: EmptyView()
                }
            }
            .task {
                await viewModel.activate()
            }
        }
    }
}

private struct ExpensesListContent : View {
    
    @State
    var categories: CategoriesState
    
    var body: some View {
        List {
            ForEach(categories.items, id: \.id) { category in
                CategoryView(category: category)
            }
        }
    }
}


struct CategoryView: View {
    var category: CategoryApplication

    var body: some View {
        VStack{
            Text(category.name)
            Text(category.description_)
        }
    }
}
