import Foundation
import composeApp

typealias KoinApplication = Koin_coreKoinApplication
typealias Koin = Koin_coreKoin

@propertyWrapper
struct LazyKoin<T> {
    lazy var wrappedValue: T = { KoinApplication.shared.inject() }()
    
    init() { }
}

extension KoinApplication {
    /// TODO Currently `networkLoggingEnabled` set as true, to be able to see network logs. Later set true/false depending on BuildType
    static let shared = companion.start(networkLoggingEnabled: true)
    
    @discardableResult
    static func start() -> KoinApplication {
        shared
    }
}

extension KoinApplication {
    
    private static let keyPaths: [PartialKeyPath<Koin>] = apiServicePath
    
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
        \.breweriesListApiService,
    ]
}
