
import Combine
import SwiftUI
import composeApp

struct BreweriesApp : View {
    
    let component: RootComponent
    
    @StateValue
    private var stack: ChildStack<AnyObject, RootChild>
    
    init(_ component: RootComponent) {
        self.component = component
        _stack = StateValue(component.stack)
    }
    
    var body: some View {
        VStack {
            switch stack.active.instance {
            case let child as RootChildHome: HomeView(component: child.component)
            default: EmptyView()
            }
        }
    }
}
