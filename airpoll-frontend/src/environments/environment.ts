// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

/**
 * @author Giacomo
 * Two initialization mode, if mode is equals to projection different approach will be used on both side (check the backend 
 * javadoc comments to clarify). On front-end side just paginator and middle data load will change 
 * */

export const environment = {
  production: false,
  mode: "projection",
  API_URL: 'http://localhost:8080/api/v1'
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
