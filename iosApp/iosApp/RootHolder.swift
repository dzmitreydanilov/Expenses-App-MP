
import SwiftUI
import composeApp

final class RootHolder : ObservableObject {
    
    let lifecycle: LifecycleRegistry
    let root: RootComponent
    
    init() {
        KoinApplication.start()
        
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
