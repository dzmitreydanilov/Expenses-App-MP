//
//  KCrypto.swift
//  ConnectivityProvider
//
//  Created by Dzmitrey Danilau on 15.01.24.
//

import Foundation
import Network

@objc public class IosNetworkHelper : NSObject {
  private let monitor: NWPathMonitor = NWPathMonitor()

   @objc public func registerListener(onNetworkAvailable: @escaping () -> Void, onNetworkLost: @escaping () -> Void) {
        monitor.pathUpdateHandler = { path in
            if path.status == .satisfied {
                onNetworkAvailable()
            } else {
                onNetworkLost()
            }
        }
        monitor.start(queue: DispatchQueue.global(qos: .background))
    }

   @objc public func unregisterListener() {
        monitor.cancel()
    }
}
