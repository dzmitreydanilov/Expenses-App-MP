import Foundation
import SwiftUI
import composeApp

class BreweriesListViewModel : ObservableObject {
    
    let component: BreweryList
    
    let viewmodel: BreweriesViewModel = KoinApplication.inject()
    
    @Published
    private(set) var state: BreweriesState = .Initial.shared
    
    init(_ component: BreweryList) {
        self.component = component
    }
    
    @MainActor
    func activate() async {
        for await state in viewmodel.breweriesState {
            self.state = state
        }
    }
    
    // Just call func, that under the hood starts coroutines, for example listening for live updates
    func collectLiveUpdates() {
        viewmodel.liveUpdate()
    }
    
    deinit {
        self.viewmodel.clear()
    }
}
