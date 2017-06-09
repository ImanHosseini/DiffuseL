![alt text](https://raw.githubusercontent.com/ImanHosseini/DiffuseL/master/lowbase.jpg)
# DiffuseL
I was having fun with diffuse lighting. I wrote an OpenMP accelerated C code, which took an sphere and a light source and traced rays (it's now called ray casting ?) and taking an expotential BDRF it calculated the brightness of the surface and outputted to a .txt file, then with java I wrote a simple class to read the file and output and show the result to a jpg. <br />
-> Notice that due to some bug, you must check the data file and replace some "nan" entries to "0.0". For some weird reason, there are exactly 8 of these buggy entries.  <br />
-> Some cool thing I did, was that to make a smoother image, I did super-sampling, so that I did a NxN grid of pixels in the C file but I drew a MxM image (M<N) this was done by taking the value of each image pixel, the average of some kxk square of pixels. Its an anti-aliasing method (the simplest I guess)  <br />
->  The fun I had, came from trying different BDRFs and different ways to illuminate, and seeing what they do in action.
