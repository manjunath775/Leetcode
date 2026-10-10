int reverseDegree(char* s) {
    int n=strlen(s),product=1,result=0;
    for(int i=0;i<n;i++){
       int value=abs(s[i]-97-26);
       product=value*(i+1);
       result=product+result;
    }
    
    return result;
    
}