import Foundation
import SwiftUI
import shared

class ExpensesListViewModel : ObservableObject {
        
    private let component: CategoryList
    
    @Published
    private(set) var state: CategoriesState = .Initial.shared
    
    init(_ compoment: CategoryList) {
        self.component = compoment
    }

    @MainActor
    func activate() async {
        for await state in component.state {
            self.state = state
        }
    }
    
    func navigateToCategoryInfo(_ id: String) {
        component.navigateToCategoryInfo(id: id)
    }
}
