.DEFAULT_GOAL := png

PUML = plantuml -charset UTF-8
SOURCES := $(shell find * -type f -name "*.puml")

PNGS = $(SOURCES:.puml=.png)
SVGS = $(SOURCES:.puml=.svg)
PDFS = $(SOURCES:.puml=.pdf)

%.png: %.puml
	$(PUML) ./$<

%.svg: %.puml
	$(PUML) -tsvg ./$<

%.pdf: %.svg
	rsvg-convert -f pdf -o $@ $<

clean:
	rm -rf $(PNGS) $(PDFS) $(SVGS)

testenv:
	$(PUML) -testdot

png: $(PNGS)
pdf: $(PDFS)
