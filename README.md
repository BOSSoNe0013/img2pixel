# img2pixel
## img to what ?
One method used to draw with CSS is to use :after pseudo-element and box-shadow argument to create pixels. It's a powerful solution but somehow complex to do by hand. This command line java tool use opencv to create the complete css for you based on an image and optionally on a pixel size (4px by default).
 [Example 1](https://www.b1project.com/avatar_pixel.html) [Example 2](https://www.b1project.com/landscape_pixel.html) [Example 3 Alpha channel](https://www.b1project.com/ratman_pixel.html)
 
## Dependencies

## Build
Use maven for build:

    mvn package -DskipTests

## Usage
```bash
java -jar img2pixel.jar avatar_80x80.png 16
```
Output:
```html
<style>
#pixel{
	width:80px;
	height:80px;
	}
#pixel:after{
	content:'';
	display:block;
	width:16px;
	height:16px;
	background:rgba(0, 0 ,0, 1.00);
	box-shadow:0px 0px rgba(0, 0 ,0, 1.00),
	0px 16px rgba(0, 0 ,0, 1.00),
	0px 32px rgba(0, 0 ,0, 1.00),
	0px 48px rgba(0, 0 ,0, 1.00),
	0px 64px rgba(0, 0 ,0, 1.00),
	16px 0px rgba(0, 0 ,0, 1.00),
	16px 16px rgba(0, 0 ,0, 1.00),
	16px 32px rgba(1, 1 ,1, 1.00),
	16px 48px rgba(3, 3 ,3, 1.00),
	16px 64px rgba(0, 0 ,0, 1.00),
	32px 0px rgba(0, 0 ,0, 1.00),
	32px 16px rgba(0, 0 ,0, 1.00),
	32px 32px rgba(219, 219 ,219, 1.00),
	32px 48px rgba(132, 132 ,132, 1.00),
	32px 64px rgba(1, 1 ,1, 1.00),
	48px 0px rgba(168, 168 ,168, 1.00),
	48px 16px rgba(151, 151 ,151, 1.00),
	48px 32px rgba(233, 233 ,233, 1.00),
	48px 48px rgba(49, 49 ,49, 1.00),
	48px 64px rgba(29, 29 ,29, 1.00),
	64px 0px rgba(0, 0 ,0, 1.00),
	64px 16px rgba(0, 0 ,0, 1.00),
	64px 32px rgba(195, 195 ,195, 1.00),
	64px 48px rgba(87, 87 ,87, 1.00),
	64px 64px rgba(12, 12 ,12, 1.00);
}
</style><div id="pixel"></div>
```

## License


Copyright 2016 Cyril Bosselut

GNU Lesser General Public License (LGPL) Version 2.1.

See [LICENSE](LICENSE).

