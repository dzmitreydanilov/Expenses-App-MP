
import SwiftUI
import composeApp

final class RootHolder {
    
    let lifecycle: LifecycleRegistry
    let root: RootComponent
    
    init() {
        self.lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        
        self.root = RootComponent(
            componentContext: DefaultComponentContext(lifecycle: lifecycle)
        )
        
        LifecycleRegistryExtKt.create(lifecycle)
    }
    
    deinit {
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}
