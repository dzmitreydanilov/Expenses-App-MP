import Foundation
import SwiftUI
import composeApp

struct BreweriesListView : View {
    
    @ObservedObject
    var viewModel: BreweriesListViewModel
    
    init(_ component: BreweryList) {
        self.viewModel = BreweriesListViewModel(component)
    }
    
    var body: some View {
        VStack {
            switch viewModel.state {
            case  let state as BreweriesState.Loading: ProgressView()
            case let state as BreweriesState.Loaded: BreweriesListContent(state, false)
            case let state as BreweriesState.Error: BreweriesListContent(state, true)
            case let state as BreweriesState.Tick: BreweriesListContent(state, false)
            default: EmptyView()
            }
        }
        .onAppear{
//            viewModel.collectLiveUpdates()
        }
        .task {
            await viewModel.activate()
        }
    }
}

private struct BreweriesListContent : View {
    
    @State
    var state: BreweriesState
    
    @State
    var isError = true
    
    init(_ breweries: BreweriesState, _ isError: Bool) {
        self.state = breweries
        self.isError = isError
    }
    
    var body: some View {
        List {
            ForEach(state.breweries, id: \.name) { brewery in
                BreweryItemView(brewery)
            }
        }.alert(isPresented: $isError, content: {
            Alert(
                title: Text("Error"),
                message: Text("Something happaned"),
                dismissButton: .default(Text("OK"))
            )
        })
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
            Text(breweryItem.address1 ?? "")
        }
    }
}
