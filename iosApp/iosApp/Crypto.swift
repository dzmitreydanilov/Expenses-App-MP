//
//  Crypto.swift
//  iosApp
//
//  Created by Dzmitrey Danilau on 12.01.24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import CryptoKit

@objc public class KCrypto : NSObject {
    @objc(md5:) public class func md5(data: NSData) -> String {
        let hashed = Insecure.MD5.hash(data: data)
        return hashed.compactMap { String(format: "%02x", $0) }.joined()
    }
}
