import Foundation
import SwiftUI
import shared

class ExpensesListViewModel : ObservableObject {
        
    let viewmodel: CategoriesListViewModel = KoinApplication.inject()
    private let networkStatus: NetworkListener = KoinApplication.inject()
    
    @Published
    private(set) var state: CategoriesListState = .Initial.shared

    @MainActor
    func activate() async {
        for await state in viewmodel.breweriesState {
            self.state = state
        }
    }
    
    func isErrorState() -> Bool {
        return self.state is CategoriesListState.Error
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
    
    func getNetworkStatus() {
        let status =  networkStatus.networkStatus
        print(String(describing: type(of: status)))
    }
    
    deinit {
//        self.viewmodel.clear()
    }
}
