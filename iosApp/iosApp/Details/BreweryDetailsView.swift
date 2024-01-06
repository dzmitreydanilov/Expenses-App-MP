
import Foundation
import SwiftUI
import shared

struct BreweryDetailsView : View {
    
    let breweryId: String
    
  
    init(_ breweryId: String) {
        self.breweryId = breweryId
    }

    var body: some View {
        VStack(alignment: /*@START_MENU_TOKEN@*/.center/*@END_MENU_TOKEN@*/) {
            Text("Brewery Details Screen")
        }.task {
          
        }
    }
}

