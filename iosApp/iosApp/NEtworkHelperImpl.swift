//
//  NEtworkHelperImpl.swift
//  iosApp
//
//  Created by Dzmitrey Danilau on 12.01.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Network
import shared

class IosNetworkHelperNew : ConnetcivityProviderHelper {
    private let monitor: NWPathMonitor = NWPathMonitor()

    func registerListener(onNetworkAvailable: @escaping () -> Void, onNetworkLost: @escaping () -> Void) {
        monitor.pathUpdateHandler = { path in
            if path.status == .satisfied {
                onNetworkAvailable()
            } else {
                onNetworkLost()
            }
        }
        monitor.start(queue: DispatchQueue.global(qos: .background))
    }
    
    func doInitFB() {
        "Ssdadasd"
    }

    func unregisterListener() {
        monitor.cancel()
    }
}
