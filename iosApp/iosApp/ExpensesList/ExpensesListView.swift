import Foundation
import SwiftUI
import shared

struct ExpensesListView : View {
    
    private let viewModel: ExpensesListViewModel
        
    init(_ component: CategoryList) {
        self.viewModel = ExpensesListViewModel(component)
    }
    
    var body: some View {
        VStack {
            ExpensesListContent(
                state: viewModel.state,
                isError: false,
                navigateToBreweryDetails: viewModel.navigateToCategoryInfo
            )
        }
        .task {
            await viewModel.activate()
        }
    }
}

private struct ExpensesListContent : View {
    
    @State
    var state: CategoriesState
    
    @State
    var isError: Bool
    
    let navigateToBreweryDetails: (String) -> Void
    
    var body: some View {
        VStack {
            List {
              
            }
            .alert(isPresented: $isError, content: {
                Alert(
                    title: Text("Error"),
                    message: Text("Something happaned"),
                    dismissButton: .default(Text("OK"))
                )
            })
        }
    }
}
