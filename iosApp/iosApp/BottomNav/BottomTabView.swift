import Foundation
import SwiftUI

struct BottomTabView : View {
    let title: String
    let systemImage: String
    let isActive: Bool
    let action: () -> Void
    
    var body: some View {
        Button(action: action){
            Label(title, systemImage: systemImage)
                .labelStyle(VerticalLabelStyle())
                .opacity(isActive ? 1 : 0.5)
        }
    }
}

private struct VerticalLabelStyle: LabelStyle {
    func makeBody(configuration: Configuration) -> some View {
        VStack(alignment: .center, spacing: 8) {
            configuration.icon
            configuration.title
        }
    }
}
