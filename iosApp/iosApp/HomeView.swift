import Foundation
import composeApp
import SwiftUI

struct HomeView : View {
    
    let component: Home
    
    @StateValue
    private var stack: ChildStack<AnyObject, HomeChild>
    
    init(component: Home) {
        self.component = component
        _stack = StateValue(component.stack)
    }
    
    var body: some View {
        VStack {
            let child = stack.active.instance
            
            ChildView(child: child).frame(maxHeight: .infinity)
            
            HStack(alignment: .bottom, spacing: 120) {
                BottomTabView(
                    title: "Breweries",
                    systemImage: "building",
                    isActive: child is HomeChild.Breweries,
                    action: {component.onTabClick(tabs: HomeTab.breweries)}
                )
                BottomTabView(
                    title: "Favorite",
                    systemImage: "heart",
                    isActive: child is HomeChild.Favorites,
                    action: {component.onTabClick(tabs: HomeTab.favorite)}
                )
            }
        }
    }
}

private struct ChildView: View {
    let child: HomeChild
    
    var body: some View {
        switch child {
        case let child as HomeChild.Breweries: BreweriesListView(child.component)
        case let child as HomeChild.Favorites: FavoritesView(component: child.component)
        default: EmptyView()
        }
    }
}
