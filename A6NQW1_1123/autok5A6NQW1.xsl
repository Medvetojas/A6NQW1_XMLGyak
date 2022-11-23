<?xml version="1.0" encoding="UTF-8" ?>

<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:template match="/">
        <html>
            <body>
                <h2>Auto típusok és példányaik száma</h2>
                <ol>
                    <xsl:for-each select = "autok/auto">
                        <xsl:sort select="ar" order="ascending"/>
                        <li>
                            <xsl:value-of select = "tipus"/>, <xsl:value-of select="count(autok/auto/tipus)= <xsl:value-of select = "tipus"/>" />
                        </li>
                    </xsl:for-each>
                </ol>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>