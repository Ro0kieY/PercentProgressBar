## PercentProgressBar

The idea of PercentProgressBar came from [here](https://www.uplabs.com/posts/line-data-visualization-design-concepts).

I decided to do this because it's an inspired UI design and i want to learn about custom view by this, even though it's simple.

****

### Demo

![img](https://github.com/Ro0kieY/PercentProgressBar/blob/master/screenshots/PercentProgressBar.gif?raw=true)

[Download Demo](https://github.com/Ro0kieY/PercentProgressBar)

****

### Usage

Step 1. Add the JitPack repository to your build file

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.Ro0kieY:PercentProgressBar:0.9'
	}
use it in your code

```
<com.ro0kiey.percentprogressbar.PercentProgressBar
    android:id="@+id/percent_progress_bar"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    app:progress_bar_style="circular"/>
```

use `progress_bar_style` attribute to decide the bar is `circular` or `horizontal`

****

### Attributes

There are several attributes you can set:

The outer border:

* outer_width
* outer_color
* outer_alpha

The progress bar:

* progress_bar_width
* progress_bar_color
* progress_bar_alpha
* current_progress
* max_progress

The text area:

* text_size
* text_color
* text_alpha

The little circle:

* little_circle_color
* little_circle_alpha

the default attributes are:

```
<com.ro0kiey.percentprogressbar.PercentProgressBar
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"

    app:outer_width="15dp"
    app:outer_color="#FFFFFF"
    app:outer_alpha="50"

    app:progress_bar_width="10dp"
    app:progress_bar_color="#5DD4CD"
    app:progress_bar_alpha="255"
    app:current_progress="0"
    app:max_progress="100"

    app:text_size="15sp"
    app:text_color="#FFFFFF"
    app:text_alpha="255"

    app:little_circle_color="#FFFFFF"
    app:little_circle_alpha="255" />
```

****

### About me 

I'm a engineer, but try to be a coder.  

