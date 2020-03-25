/**
 * Constantes globales
 *
 * @author www.redstone.nc
 *
 */

'use strict';

export const jwtItemName: string = 'GEODE-JWT-Token';
export const apiEndPoint: string = 'http://localhost:8080/geode';
export const oAuthCallbackUrl: string = 'http://localhost:4200/access/';
export const providers = [
    { name: 'google' },
    { name: 'facebook' },
    { name: 'twitter' },
    { name: 'linkedin' }
];
