# SpringBoot + OCR (Tesseract)
El proyecto tiene como objetivo crear una API con la cual se pueda extraer texto de imágenes en varios formatos. La aplicacion esta desarrollado con el framework SpringBoot y API de Tesseract (Tess4J).
La imagen es procesada para obtener un mejor resultado, aplicando una serie de filtro:
- Filtro de Blanco y Negro
- Suavizado (quitar el ruido de la imagen)
- Nitidez

## Idiomas
Los idiomas que soporta esta API son los siguientes:
- CHI (Chino)
- DEU (Aleman)
- ENG (Ingles)
- FRA (Frances)
- JPN (Japones)
- KOR (Koreano)
- POR (POrtugues)
- SPA (Español)

Los archivos de idiomas se encuentran en la carpeta:
> tessdata

Para agregar mas idiomas, es necesario descargar el archivo del siguiente [Repositorio](https://github.com/tesseract-ocr/tessdata), agregarlo a la carpeta y agregar el idioma al ENUM Language
### Imagenes
Los formatos soportados para esta API son los siguientes:
- JPEG
- JPG
- PNG
- TIFF

#### Visualizar endpoints
Si todo esta configurado correctamente, podemos iniciar la aplicacion y probar los endpoints con la ayuda Swagger, en la siguiente direccion:
> `http://localhost:{puerto}/swagger-ui/index.html`
