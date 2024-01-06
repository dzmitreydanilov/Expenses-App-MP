import Foundation
import SwiftUI
import shared

class ExpensesListViewModel : ObservableObject {
        
    let viewmodel: BreweriesViewModel = KoinApplication.inject()
    
    @Published
    private(set) var state: BreweriesState = .Initial.shared

    @MainActor
    func activate() async {
        for await state in viewmodel.breweriesState {
            self.state = state
        }
    }
    
    func isErrorState() -> Bool {
        return self.state is BreweriesState.Error
    }
    
    // Just call func, that under the hood starts coroutines, for example listening for live updates
    func collectLiveUpdates() {
        viewmodel.collectLiveUpdates()
    }
    
    func refreshBrewerylist(){
        viewmodel.getBreweriesList()
    }
    
    func getBreweriesList() {
        viewmodel.getBreweriesList()
    }
    
    func getBreweriesListWithError() {
        viewmodel.getBreweriesListWithError()
    }
    
    deinit {
//        self.viewmodel.clear()
    }
}
