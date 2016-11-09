# img2pixel
## img to what ?
One method used to draw with CSS is to use :after pseudo-element and box-shadow argument to create pixels. It's a powerful solution but somehow complex to do by hand. This command line java tool use opencv to create the complete css for you based on an image and optionally on a pixel size (4px by default).
 [Example 1](https://www.b1project.com/avatar_pixel.html) [Example 2](https://www.b1project.com/landscape_pixel.html)
 
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
	background:#000000;
	box-shadow:0px 0px #000000,
	0px 16px #000000,
	0px 32px #000000,
	0px 48px #000000,
	0px 64px #000000,
	16px 0px #000000,
	16px 16px #000000,
	16px 32px #010101,
	16px 48px #030303,
	16px 64px #000000,
	32px 0px #000000,
	32px 16px #000000,
	32px 32px #DBDBDB,
	32px 48px #848484,
	32px 64px #010101,
	48px 0px #A8A8A8,
	48px 16px #979797,
	48px 32px #E9E9E9,
	48px 48px #313131,
	48px 64px #1D1D1D,
	64px 0px #000000,
	64px 16px #000000,
	64px 32px #C3C3C3,
	64px 48px #575757,
	64px 64px #0C0C0C;
}
</style><div id="pixel"></div>
```

## License

GNU Lesser General Public License (LGPL) Version 2.1.

See [LICENSE](LICENSE).

