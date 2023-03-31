

public class func {    
    double b;
    int x;
    double y1, y2;
    double h = 0.1;

    func(String b){
        this.b = Double.parseDouble(b);
    }

    public double Z(double x){
        return Math.log((x+1)/(x-1))/2+b;
    }

    public double Y(double x){
        double t = 1/x;
        double s = t;
        int n = 0;
        double e = 0.0001;

        while(Math.abs(t)/(2*n+1)>e){
            n += 1;
            t = t/x/x;
            s += t/(2*n+1);
        }
        return s;
    }

}
