#include <stdio.h>
#include <xmmintrin.h>

int main(){
    float a[4] = {1,2,3,4};
    float b[4] = {5,6,7,8};
    float result[4];

    __m128 va = _mm_loadu_ps(a); // Load array a into a SIMD register
    __m128 vb = _mm_loadu_ps(b); // Load array b into a SIMD register
    __m128 vresult = _mm_add_ps(va, vb); // Perform SIMD addition
    _mm_storeu_ps(result, vresult); // Store the result back to an array

    for(int i = 0; i < 4; i++) {
        printf("result[%d] = %.1f\n", i, result[i]);
    }

    return 0;
}
