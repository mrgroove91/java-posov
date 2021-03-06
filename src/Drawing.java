import java.util.Arrays;

public class Drawing implements Printable{

    char[][] canvas;

    public Drawing(int lines, int rows, char symbol) {
        this.canvas = new char[lines][rows];
        for (char[] line : this.canvas) {
            Arrays.fill(line, symbol);
        }
    }

    public void print() {
        for (char[] line : canvas) {
            for (char c : line) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    public void setPoint(int x, int y, char c) {
        this.canvas[(this.canvas.length - y - 1)][x] = c;
    }

    public void drawVerticalLine(int x, int y, int length, char symbol) {
        for (int i = 0; i < length; i++) {
            if (x < this.canvas[0].length && y + i < this.canvas.length) {
                this.setPoint(x, y + i, symbol);
            }
        }
    }

    public void drawHorizontalLine(int x, int y, int length, char symbol) {
        for (int i = 0; i < length; i++) {
            if (x + i < this.canvas[0].length && y < this.canvas.length) {
                this.setPoint(x + i, y, symbol);
            }
        }
    }

    //Кусок кода из открытых источников
    private int sign(int x) {
        return (x > 0) ? 1 : (x < 0) ? -1 : 0;
        //возвращает 0, если аргумент (x) равен нулю; -1, если x < 0 и 1, если x > 0.
    }

    public void drawLine(int xstart, int ystart, int xend, int yend, char symbol)
    /**
     * xstart, ystart - начало;
     * xend, yend - конец;
     * "g.drawLine (x, y, x, y);" используем в качестве "setPixel (x, y);"
     * Можно писать что-нибудь вроде g.fillRect (x, y, 1, 1);
     */
    {
        int x, y, dx, dy, incx, incy, pdx, pdy, es, el, err;

        dx = xend - xstart;//проекция на ось икс
        dy = yend - ystart;//проекция на ось игрек

        incx = sign(dx);
        /*
         * Определяем, в какую сторону нужно будет сдвигаться. Если dx < 0, т.е. отрезок идёт
         * справа налево по иксу, то incx будет равен -1.
         * Это будет использоваться в цикле постороения.
         */
        incy = sign(dy);
        /*
         * Аналогично. Если рисуем отрезок снизу вверх -
         * это будет отрицательный сдвиг для y (иначе - положительный).
         */

        if (dx < 0) dx = -dx;//далее мы будем сравнивать: "if (dx < dy)"
        if (dy < 0) dy = -dy;//поэтому необходимо сделать dx = |dx|; dy = |dy|
        //эти две строчки можно записать и так: dx = Math.abs(dx); dy = Math.abs(dy);

        if (dx > dy)
        //определяем наклон отрезка:
        {
            /*
             * Если dx > dy, то значит отрезок "вытянут" вдоль оси икс, т.е. он скорее длинный, чем высокий.
             * Значит в цикле нужно будет идти по икс (строчка el = dx;), значит "протягивать" прямую по иксу
             * надо в соответствии с тем, слева направо и справа налево она идёт (pdx = incx;), при этом
             * по y сдвиг такой отсутствует.
             */
            pdx = incx;
            pdy = 0;
            es = dy;
            el = dx;
        } else//случай, когда прямая скорее "высокая", чем длинная, т.е. вытянута по оси y
        {
            pdx = 0;
            pdy = incy;
            es = dx;
            el = dy;//тогда в цикле будем двигаться по y
        }

        x = xstart;
        y = ystart;
        err = el / 2;
        this.setPoint(x, y, symbol);//ставим первую точку
        //все последующие точки возможно надо сдвигать, поэтому первую ставим вне цикла

        for (int t = 0; t < el; t++)//идём по всем точкам, начиная со второй и до последней
        {
            err -= es;
            if (err < 0) {
                err += el;
                x += incx;//сдвинуть прямую (сместить вверх или вниз, если цикл проходит по иксам)
                y += incy;//или сместить влево-вправо, если цикл проходит по y
            } else {
                x += pdx;//продолжить тянуть прямую дальше, т.е. сдвинуть влево или вправо, если
                y += pdy;//цикл идёт по иксу; сдвинуть вверх или вниз, если по y
            }

            this.setPoint(x, y, symbol);
        }
    }

    void drawCircle(int x0, int y0, int radius, char symbol) {
        int x = 0;
        int y = radius;
        int delta = 1 - 2 * radius;
        int error = 0;
        while (y >= 0) {
            this.setPoint(x0 + x, y0 + y, symbol);
            this.setPoint(x0 + x, y0 - y, symbol);
            this.setPoint(x0 - x, y0 + y, symbol);
            this.setPoint(x0 - x, y0 - y, symbol);
            error = 2 * (delta + y) - 1;
            if (delta < 0 && error <= 0) {
                ++x;
                delta += 2 * x + 1;
                continue;
            }
            error = 2 * (delta - x) - 1;
            if (delta > 0 && error > 0) {
                --y;
                delta += 1 - 2 * y;
                continue;
            }
            ++x;
            delta += 2 * (x - y);
            --y;
        }
    }
    //Конец открытых источников

    public void drawRectangle(int x1, int y1, int x2, int y2, char symbol) {
        this.drawHorizontalLine(x1, y1, x2 - x1 + 1, symbol);
        this.drawHorizontalLine(x1, y2, x2 - x1 + 1, symbol);
        this.drawVerticalLine(x1, y1, y2 - y1 + 1, symbol);
        this.drawVerticalLine(x2, y1, y2 - y1 + 1, symbol);

    }


    public void draw(int x1, int y1, Drawing drawing) {
        for (int y = 0; y < drawing.canvas.length; y++) {
            for (int x = 0; x < drawing.canvas[y].length; x++) {
                this.setPoint(x1 + x, y1 + y, drawing.canvas[drawing.canvas.length - y - 1][x]);
            }
        }

    }


}
