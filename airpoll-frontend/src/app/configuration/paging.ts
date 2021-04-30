import { environment } from "src/environments/environment";

/**
 * @author Giacomo
 * If mode equals projection page starts by 0, and next pack of data will be loaded and added to previous while scrolling.
 * If mode is not projection page starts by 4 (a resonable start value time load) and more data will be loaded at each scroll and
 * will replace the previous (By specification or classic JPA Pageable Interface there isn't a way to limit 
 * the Object relationships).
 * 
 */

export class Paging {
    page: number;

    constructor() {
        if (environment.mode === 'projection') this.page = 0;
        else this.page = 4;
    }

    increase():void {
        this.page++;
    }
    decrease():void {
        this.page--;
    }
    reset():void {
        if (environment.mode === 'projection') this.page = 0;
        else this.page = 4;
    }
}