import Foundation
import composeApp

class FavoriteViewModel : ObservableObject {
    
//    let viewModel: FavoriteBreweryViewModel = KoinApplication.inject()
    
//    @Published
//    private(set) var state: FavoriteBreweryState = .Initial.shared
//    
//    @MainActor
//    func activate() async {
////        do {
////            let sequence = asyncSequence(for: viewModel.favoriteBreweryStateFlow)
////            for try await value in sequence{
////                self.state = value
////            }
////        } catch {
////            print("Error: \(error)")
////        }
//    }
//    
//    @MainActor
//    func getBrewery() async {
//        do {
//            let breweryState = try await asyncFunction(for: viewModel.fetchFavoriteBreweryById())
//            self.state = breweryState
//        } catch {
//            self.state = FavoriteBreweryState.Error(error: KotlinThrowable(message: error.localizedDescription))
//        }
//    }
//    
//    deinit {
//        self.viewModel.clear()
//    }
}
