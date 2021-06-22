const version = process.env.REACT_APP_VERSION;
const STORAGE_KEY = `${version}_`;

export function saveItem(key: string, value: string): boolean {
    if (!localStorage) {
        return false;
    }
    try {
        localStorage.setItem(STORAGE_KEY + key, value);
        return true;
    } catch (error) {
        throw new Error('store serialization failed');
    }
}

export function loadItem(key: string): string | null {
    if (!localStorage) {
        return null;
    }
    const value = localStorage.getItem(STORAGE_KEY + key);
    return value;
}


export function removeItem(key: string) {
    if (!localStorage) {
        return;
    }
    localStorage.removeItem(STORAGE_KEY + key);

}