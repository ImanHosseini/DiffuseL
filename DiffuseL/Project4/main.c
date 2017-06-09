/*
* Seyed Iman Hosseini Zavaraki
* Github @ https://github.com/ImanHosseini
* Wordpress @ https://imanhosseini.wordpress.com/
*/



#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <omp.h>


#define R 1.0
#define L 8.0
#define Xs 5.0
#define Ys 0.3
#define Zs 2.8
#define DEL 0.51
#define Ng 4000
#define THREAD_NUM 32
#define EPS 0.0000000000000000000001





_inline double dot(double a[3], double b[3]) {
	return a[0] * b[0] + a[1] * b[1] + a[2] * b[2];
}

_inline double norm2(double a[3]) {
	return a[0] * a[0] + a[1] * a[1] + a[2] * a[2];
}





double bright[Ng][Ng] = { 0.0 };
double rs[Ng][Ng] = { 0.0 };

int main(int argc, char** argv) {


	int Nr = Ng*Ng;
	omp_set_num_threads(THREAD_NUM);
	int excess = Nr - (Nr / THREAD_NUM)*THREAD_NUM;
	const double vp[3] = { 0.0,0.0,0.0 };
	const double src[3] = { Xs,Ys,Zs };
#pragma omp parallel
	{
		int iam = omp_get_thread_num();
		int begin = iam*(Nr / THREAD_NUM);
		int end;
		if ((iam + 1) == THREAD_NUM) end = Nr - 1;
		else end = (iam + 1)*(Nr / THREAD_NUM) - 1;
		for (int i = begin; i < end; i++) {
			int zi = i / Ng;
			int yi = i%Ng;
			double y = -R + (((double)yi)*2.0*R) / (double)Ng;
			double z = -R + (((double)zi)*2.0*R) / (double)Ng;
			if (hypot(z, y) > R) continue;
			double sp[3] = { L - sqrt(R*R - z*z - y*y),y,z };
			double normal[3] = { (-L + sp[0]) / R,sp[1] / R,sp[2] / R };
			double ptos[3] = { Xs - sp[0], Ys - sp[1], Zs - sp[2] };
			//printf("%.14f - %.14f - %.14f\n", normal[0], normal[1], normal[2]);
			double betain = acos(dot(ptos, normal) / sqrt(norm2(ptos)));
			double betaout = acos(-dot(sp, normal) / sqrt(norm2(sp)));
			double del = fabs(betain);
			//Other Choices
			// del = fabs(betaout);
			//del= fabs(betain-betaout);
			//printf("%.16f - %.16f - %.16f\n", del,betain,betaout);
			if (del> 5.0*DEL) continue;

			double dist2 = norm2(ptos);
			double pwr = 100.0*exp(-del / DEL)/dist2;
			//double redness = exp(-del / DEL);
			bright[yi][zi] = pwr;
			//rs[yi][zi] = redness;
		}
	}


	FILE *fp;
	fp = fopen("/tmp/data1.txt", "w+");
	for (int i = 0; i < Ng; i++) {
		for (int j = 0; j < Ng; j++) {
			fprintf(fp, "%.15f  ", bright[i][j]);
		}
		fprintf(fp, "\n");
	}
	/*for (int i = 0; i < Ng; i++) {
		for (int j = 0; j < Ng; j++) {
			fprintf(fp, "%.15f  ", rs[i][j]);
		}
		fprintf(fp, "\n");
	}*/
	fclose(fp);



}

