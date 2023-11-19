import Foundation
import SwiftUI
import composeApp

struct BreweriesListView : View {
    
    @ObservedObject
    var viewModel: BreweriesListViewModel = BreweriesListViewModel()
    
    var body: some View {
        VStack {
            switch viewModel.state {
            case  _ as BreweriesState.Loading: ProgressView()
            case let state as BreweriesState.Loaded: BreweriesListContent(state.breweries)
            default: EmptyView()
            }
        }
        .task {
            await viewModel.activate()
        }
    }
}

private struct BreweriesListContent : View {
    
    let breweries: [Brewery]
    
    init(_ breweries: [Brewery]) {
        self.breweries = breweries
    }
    
    var body: some View {
        List {
            ForEach(breweries, id: \.name) { brewery in
                BreweryItemView(brewery)
            }
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
            Text(breweryItem.address1 ?? "")
        }
    }
}
