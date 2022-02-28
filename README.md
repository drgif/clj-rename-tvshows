# clj-rename-tvshows

Traverses a directory structure containing TV Shows in the format:
```
basepath/
|--- ALF/
|    |--- Season 01/
|    |    |--- 01 - Pilot.avi
|    |    |--- 02 - The Second.avi
|    |--- Season 02

...
```
and adds the TV Show and season information to the respective media files like this:
```
basepath/
|--- ALF/
|    |--- Season 01/
|    |    |--- ALF.01x01 - Pilot.avi
|    |    |--- ALF.01x02 - The Second.avi
|    |--- Season 02

...
```
In this format, the files can be properly analyzed using [mnamer](https://github.com/jkwill87/mnamer).

## Installation

Download from https://github.com/mwiederhold/clj-rename-tvshows.

## Usage

Execute the jar with one argument consisting of the basepath to your TV Shows:

    $ java -jar clj-rename-tvshows-0.1.0-standalone.jar [basepath]


## Examples

    $ java -jar clj-rename-tvshows-0.1.0-standalone.jar /media/tvshows

## Bugs

This tool is not well-tested. Use at your own risk.

## License

Copyright © 2022 Michael Wiederhold

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
