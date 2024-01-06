import SwiftUI
import shared

struct BreweriesListComposeView : View {
    
    let component: BreweryList
    
    init(component: BreweryList) {
        self.component = component
    }
    
    var body: some View {
        BreweriesListContentView(component: component).ignoresSafeArea(edges: .bottom)
    }
}
