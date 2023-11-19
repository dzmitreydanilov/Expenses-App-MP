import Foundation
import SwiftUI
import composeApp

@MainActor
class BreweriesListViewModel : ObservableObject {
        
    let component: BreweryList
    
    @Published
    private(set) var state: BreweriesState = .Initial.shared
    
    init(_ component: BreweryList) {
        self.component = component
    }
    
    func activate() async {
        for await state in component.state {
            self.state = state
        }
    }
    /// We don't need it, because Decompose Component tight with the The lifecycle of the View
//    deinit {
//        self.viewModel.clear()
//    }
}
