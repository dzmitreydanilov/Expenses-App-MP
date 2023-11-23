
import Foundation
import KMMViewModelCore
import SwiftUI
import composeApp
import KMMViewModelSwiftUI

struct BreweryDetailsView : View {
    
    let breweryId: String
    
    @StateViewModel
    var viewModel: BreweryDetailsViewModel = KoinApplication.inject()
    
    init(_ breweryId: String) {
        self.breweryId = breweryId
    }

    var body: some View {
        VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/) {
            Text("Brewery Details Screen")
        }.task {
            viewModel.collectLiveUpdate()
        }
    }
}

extension Kmm_viewmodel_coreKMMViewModel: KMMViewModel { }
