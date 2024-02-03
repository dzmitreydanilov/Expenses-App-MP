import Foundation
import shared

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = { KoinApplication.shared.inject() }()
    
    init() { }
}

extension KoinApplication {
    static let shared = companion.start(
        networkLoggingEnabled: true)
    
    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {
    
    private static let keyPaths: [PartialKeyPath<Koin>] = apiServicePath + viewModelsPath
    
    static func inject<T>() -> T {
        shared.inject()
    }
    
    func inject<T>() -> T {
        for partialKeyPath in Self.keyPaths {
            guard let keyPath = partialKeyPath as? KeyPath<Koin, T> else { continue }
            return koin[keyPath: keyPath]
        }
        
        fatalError("\(T.self) is not registered with KoinApplication")
    }
}

extension KoinApplication {
    
    private static let apiServicePath: [PartialKeyPath<Koin>] = [
        
    ]
    
    private static let viewModelsPath: [PartialKeyPath<Koin>] = [
         \.networkStatus,
    ]
}
