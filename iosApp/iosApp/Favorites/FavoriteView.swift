
import Foundation
import shared
import SwiftUI

struct FavoriteView : View {
    
    @ObservedObject
    var viewModel: FavoriteViewModel
    
    init() {
        self.viewModel = FavoriteViewModel()
    }
    
    var body: some View {
        Text("HELLO")
        }
}
