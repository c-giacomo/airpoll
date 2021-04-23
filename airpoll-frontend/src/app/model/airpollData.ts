export class AirpollData {
    id: Number;

    location: String;
	city: String;
	country: String;
	
	pm25?: Number;
	no2?: Number;
	co?: Number;
	pm10?: Number;
	o3?: Number;
	
	latitude?: String;
	longitude?: String;
	
	local?: Date;

    constructor() {}
}