
import SwiftUI
import shared
import Firebase

final class RootHolder : ObservableObject {
    
    let lifecycle: LifecycleRegistry
    let root: RootComponent
    
    init() {
        KoinApplication.start()
        
        let crypto = KCrypto()
        
        self.lifecycle = LifecycleRegistryKt.LifecycleRegistry()
        
        self.root = RootComponent(
            componentContext: DefaultAppComponentContext(componentContext: DefaultComponentContext(lifecycle: lifecycle))
        )
        
        LifecycleRegistryExtKt.create(lifecycle)
    }
    
    deinit {
        LifecycleRegistryExtKt.destroy(lifecycle)
    }
}
