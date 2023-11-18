import SwiftUI
import composeApp

struct BreweriesListView : View {
    
    let component: BreweryList
    
    init(component: BreweryList) {
        self.component = component
    }
    
    var body: some View {
        BreweriesListContentView(component: component).ignoresSafeArea(edges: .bottom)
    }
}

struct BreweriesListContentView: UIViewControllerRepresentable {
    
    let component: BreweryList
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainKt.BreweriesViewController(component: component)
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}
