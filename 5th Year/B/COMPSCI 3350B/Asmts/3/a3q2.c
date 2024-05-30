int exer2a(int v){
    int i = 0;
    while(v > 0){
        v = exer2b(v);
        i++;
    }
    return i;
    }

int exer2b(int v){
    int t = v + 1;
    return t >> 2;
}
