SUMMARY = "Text file viewer similar to more"
DESCRIPTION = "Less is a program similar to more, i.e. a terminal \
based program for viewing text files and the output from other \
programs. Less offers many features beyond those that more does."
HOMEPAGE = "http://www.greenwoodsoftware.com/"
SECTION = "console/utils"

LICENSE = "GPLv3+ | BSD-2-Clause"
LIC_FILES_CHKSUM = "file://COPYING;md5=d32239bcb673463ab874e80d47fae504 \
                    file://LICENSE;md5=ba01d0cab7f62f7f2204c7780ff6a87d \
                    "
DEPENDS = "ncurses"

SRC_URI = "file://less-563.tar.gz"

UPSTREAM_CHECK_URI = "http://www.greenwoodsoftware.com/less/download.html"

inherit autotools update-alternatives

do_install () {
        oe_runmake 'bindir=${D}${bindir}' 'mandir=${D}${mandir}' install
}

ALTERNATIVE_${PN} = "less"
ALTERNATIVE_PRIORITY = "100"
