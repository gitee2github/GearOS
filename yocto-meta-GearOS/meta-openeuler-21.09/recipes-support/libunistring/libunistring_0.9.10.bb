SUMMARY = "Library for manipulating C and Unicode strings"

DESCRIPTION = "Text files are nowadays usually encoded in Unicode, and may\
 consist of very different scripts from Latin letters to Chinese Hanzi\
 with many kinds of special characters accents, right-to-left writing\
 marks, hyphens, Roman numbers, and much more. But the POSIX platform\
 APIs for text do not contain adequate functions for dealing with\
 particular properties of many Unicode characters. In fact, the POSIX\
 APIs for text have several assumptions at their base which don't hold\
 for Unicode text.  This library provides functions for manipulating\
 Unicode strings and for manipulating C strings according to the Unicode\
 standard.  This package contains documentation."

HOMEPAGE = "http://www.gnu.org/software/libunistring/"
SECTION = "devel"
LICENSE = "LGPLv3+ | GPLv2"
LIC_FILES_CHKSUM = "file://COPYING.LIB;md5=6a6a8e020838b23406c81b19c1d46df6 \
                    file://README;beginline=45;endline=65;md5=08287d16ba8d839faed8d2dc14d7d6a5 \
                    file://doc/libunistring.texi;md5=287fa6075f78a3c85c1a52b0a92547cd \
                   "

SRC_URI = "file://libunistring-0.9.10.tar.xz \
           file://0001-Unset-need_charset_alias-when-building-for-musl.patch \
"
SRC_URI[md5sum] = "db08bb384e81968957f997ec9808926e"
SRC_URI[sha256sum] = "eb8fb2c3e4b6e2d336608377050892b54c3c983b646c561836550863003c05d7"

inherit autotools texinfo
BBCLASSEXTEND = "native nativesdk"
