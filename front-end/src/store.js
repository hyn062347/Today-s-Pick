// src/store.js
import { writable } from 'svelte/store';
export const user = writable(null);
export const favorite = writable(null);
