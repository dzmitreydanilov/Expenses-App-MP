import Foundation
import SwiftUI
import shared

struct BreweriesListView : View {
    
    @ObservedObject
    var viewModel: ExpensesListViewModel
    
    let component: CategoryList
    
    init(_ component: CategoryList) {
        self.viewModel = ExpensesListViewModel()
        self.component = component
    }
    
    var body: some View {
        VStack {
            BreweriesListContent(
                state: viewModel.state,
                isError: viewModel.isErrorState(),
                btnAction: viewModel.getBreweriesListWithError,
                navigateToBreweryDetails: component.navigateBreweryDetails
            )
        }.refreshable {
            viewModel.refreshBrewerylist()
        }
        .onAppear {
            viewModel.getBreweriesList()
            viewModel.collectLiveUpdates()
        }
        .task {
            await viewModel.activate()
        }
    }
}

private struct BreweriesListContent : View {
    
    
    
    @State
    var state: CategoriesListState
    
    @State
    var isError: Bool
    
    let btnAction: () -> Void
    
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
            
            HStack(
                content: {
                    Button(
                        action: {
                            btnAction()
                        },
                        label: { Text("Generate Error State") }
                    )
                }
            )
        }
    }
}

private struct BreweryItemView : View {
    
    let breweryItem: Brewery
    
    init(_ brewery: Brewery) {
        self.breweryItem = brewery
    }
    
    var body: some View {
        HStack {
            Text(breweryItem.name ?? "")
        }
    }
}
