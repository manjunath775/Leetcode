int maxDepth(char* s) {
     int n=strlen(s),max=0,count=0;
    for(int i=0;i<n;i++){
        if(s[i]=='('){
             count=count+1;
            }
        if(s[i]==')'){
            count=count-1;
        }
        if(count>max){
            max=count;
        }
    }
    return max;
}