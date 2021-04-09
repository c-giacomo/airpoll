export class AirpollData {
    private id:Number;

    private location:String;
	private city:String;
	private country:String;
	
	private pm25?:Number;
	private no2?:Number;
	private co?:Number;
	private pm10?:Number;
	private o3?:Number;
	
	private latitude?:String;
	private longitude?:String;
	
	private local?:Date;

    constructor() {}
}