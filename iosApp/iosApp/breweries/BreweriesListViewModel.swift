import Foundation
import SwiftUI
import composeApp

@MainActor
class BreweriesListViewModel : ObservableObject {
    
    private let viewModel: BreweriesViewModel = KoinApplication.inject()
    
    @Published
    private(set) var state: BreweriesState = .Initial.shared
    
    func activate() async {
        for await state in viewModel.breweriesState {
            self.state = state
        }
    }
    
    func fetchBreweries() {
//        viewModel.getBreweriesList()
    }
    
    deinit {
        self.viewModel.clear()
    }
}
