declare global {
  interface Window {
    showToast: (message: string, type?: 'success' | 'error') => void;
    updateBasketCount?: () => void;
    selectGalette?: (id: number) => void;
    toggleSauce?: (id: number) => void;
    updateTopping?: (id: number, change: number) => void;
    updateQuantity?: (itemId: number, newQty: number) => void;
    removeItem?: (itemId: number) => void;
  }
}

export {};