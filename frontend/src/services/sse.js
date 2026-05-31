import { API_BASE } from '../config/Constants';

export const createSSEConnection = (endpoint, params, callbacks) => {
    // Build SSE URL directly from the backend API base.
    // Do NOT add window.location.origin because API_BASE is already a full backend URL.
    const url = new URL(`${API_BASE}${endpoint}`);

    if (params) {
        Object.keys(params).forEach((key) => {
            if (params[key] !== undefined && params[key] !== null) {
                url.searchParams.append(key, params[key]);
            }
        });
    }

    const eventSource = new EventSource(url.toString());

    if (callbacks?.onOpen) {
        eventSource.onopen = callbacks.onOpen;
    }

    if (callbacks?.onError) {
        eventSource.onerror = callbacks.onError;
    }

    if (callbacks?.onMessage) {
        eventSource.onmessage = callbacks.onMessage;
    }

    if (callbacks?.events) {
        Object.keys(callbacks.events).forEach((eventName) => {
            eventSource.addEventListener(eventName, callbacks.events[eventName]);
        });
    }

    return eventSource;
};