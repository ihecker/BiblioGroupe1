FROM node:lts-bullseye AS builder

WORKDIR /app

COPY package.json.

RUN npm install

COPY ..

RUN ./node_modules/.bin/ng build

FROM ngnix:latest

COPY --from=builder /app/dist/biblio-angular/browser /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]


