
import Foundation
import composeApp
import SwiftUI

struct FavoriteView : View {
    
    @ObservedObject
    var viewModel: FavoriteViewModel
    
    init() {
        self.viewModel = FavoriteViewModel()
    }
    
    var body: some View {
        Text(viewModel.state.brewery?.name ?? "Loading... ")
            .task {
                Task {
                 await viewModel.getBrewery()
                }
             await viewModel.activate()
        }
    }
}
