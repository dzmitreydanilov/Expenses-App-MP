import SwiftUI
import composeApp

struct FavoritesView : View {
    
    let component: Favorite
    
    init(component: Favorite) {
        self.component = component
    }
    
    var body: some View {
        FavoritesContentView(component: component).ignoresSafeArea(edges: .bottom)
    }
}

struct FavoritesContentView: UIViewControllerRepresentable {
    
    let component: Favorite
    
    func makeUIViewController(context: Context) -> some UIViewController {
        MainKt.FavoritesViewController(component: component)
    }
    
    func updateUIViewController(_ uiViewController: UIViewControllerType, context: Context) {
        
    }
}
